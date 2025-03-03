package nc.crafting.workspace;

import static nc.crafting.workspace.NuclearWorkspaceRecipeSorter.Category.SHAPED;
import static nc.crafting.workspace.NuclearWorkspaceRecipeSorter.Category.SHAPELESS;
import static nc.crafting.workspace.NuclearWorkspaceRecipeSorter.Category.UNKNOWN;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.crafting.IRecipe;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.toposort.TopologicalSort;
import cpw.mods.fml.common.toposort.TopologicalSort.DirectedGraph;

public class NuclearWorkspaceRecipeSorter implements Comparator<IRecipe> {

    public enum Category {
        UNKNOWN,
        SHAPELESS,
        SHAPED
    };

    private static class SortEntry {

        private String name;
        private Class<?> cls;
        private Category cat;
        List<String> before = Lists.newArrayList();
        List<String> after = Lists.newArrayList();

        private SortEntry(String name, Class<?> cls, Category cat, String deps) {
            this.name = name;
            this.cls = cls;
            this.cat = cat;
            parseDepends(deps);
        }

        private void parseDepends(String deps) {
            if (deps.isEmpty()) return;
            for (String dep : deps.split(" ")) {
                if (dep.startsWith("before:")) {
                    before.add(dep.substring(7));
                } else if (dep.startsWith("after:")) {
                    after.add(dep.substring(6));
                } else {
                    throw new IllegalArgumentException("Invalid dependancy: " + dep);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append("RecipeEntry(\"").append(name).append("\", ");
            buf.append(cat.name()).append(", ");
            buf.append(cls == null ? "" : cls.getName()).append(")");

            if (before.size() > 0) {
                buf.append(" Before: ").append(Joiner.on(", ").join(before));
            }

            if (after.size() > 0) {
                buf.append(" After: ").append(Joiner.on(", ").join(after));
            }

            return buf.toString();
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    };

    @SuppressWarnings("rawtypes")
    private static Map<Class, Category> categories = Maps.newHashMap();
    // private static Map<String, Class> types = Maps.newHashMap();
    private static Map<String, SortEntry> entries = Maps.newHashMap();
    @SuppressWarnings("rawtypes")
    private static Map<Class, Integer> priorities = Maps.newHashMap();

    public static NuclearWorkspaceRecipeSorter INSTANCE = new NuclearWorkspaceRecipeSorter();
    private static boolean isDirty = true;

    private static SortEntry before = new SortEntry("Before", null, UNKNOWN, "");
    private static SortEntry after = new SortEntry("After", null, UNKNOWN, "");

    public NuclearWorkspaceRecipeSorter() {
        register("nc:shaped", NuclearWorkspaceShapedRecipes.class, SHAPED, "before:nc:shapeless");
        register("nc:shapeless", NuclearWorkspaceShapelessRecipes.class, SHAPELESS, "after:nc:shaped");

        register("nc:shapedore", NuclearWorkspaceShapedOreRecipe.class, SHAPED, "after:nc:shaped before:nc:shapeless");
        register("nc:shapelessore", NuclearWorkspaceShapelessOreRecipe.class, SHAPELESS, "after:nc:shapeless");
    }

    @Override
    public int compare(IRecipe r1, IRecipe r2) {
        Category c1 = getCategory(r1);
        Category c2 = getCategory(r2);
        if (c1 == SHAPELESS && c2 == SHAPED) return 1;
        if (c1 == SHAPED && c2 == SHAPELESS) return -1;
        if (r2.getRecipeSize() < r1.getRecipeSize()) return -1;
        if (r2.getRecipeSize() > r1.getRecipeSize()) return 1;
        return getPriority(r2) - getPriority(r1); // high priority value first!
    }

    @SuppressWarnings("rawtypes")
    private static Set<Class> warned = Sets.newHashSet();

    @SuppressWarnings("unchecked")
    public static void sortCraftManager() {
        bake();
        FMLLog.fine("Sorting recipies");
        warned.clear();
        Collections.sort(NuclearWorkspaceCraftingManager.getInstance().getRecipeList(), INSTANCE);
    }

    public static void register(String name, Class<?> recipe, Category category, String dependancies) {
        assert (category != UNKNOWN) : "Category must not be unknown!";
        isDirty = true;

        SortEntry entry = new SortEntry(name, recipe, category, dependancies);
        entries.put(name, entry);
        setCategory(recipe, category);
    }

    public static void setCategory(Class<?> recipe, Category category) {
        assert (category != UNKNOWN) : "Category must not be unknown!";
        categories.put(recipe, category);
    }

    public static Category getCategory(IRecipe recipe) {
        return getCategory(recipe.getClass());
    }

    public static Category getCategory(Class<?> recipe) {
        Class<?> cls = recipe;
        Category ret = categories.get(cls);

        if (ret == null) {
            while (cls != Object.class) {
                cls = cls.getSuperclass();
                ret = categories.get(cls);
                if (ret != null) {
                    categories.put(recipe, ret);
                    return ret;
                }
            }
        }

        return ret == null ? UNKNOWN : ret;
    }

    private static int getPriority(IRecipe recipe) {
        Class<?> cls = recipe.getClass();
        Integer ret = priorities.get(cls);

        if (ret == null) {
            if (!warned.contains(cls)) {
                FMLLog.info(
                        "  Unknown recipe class! %s Modder please refer to %s",
                        cls.getName(),
                        NuclearWorkspaceRecipeSorter.class.getName());
                warned.add(cls);
            }
            cls = cls.getSuperclass();
            while (cls != Object.class) {
                ret = priorities.get(cls);
                if (ret != null) {
                    priorities.put(recipe.getClass(), ret);
                    FMLLog.fine("    Parent Found: %d - %s", ret.intValue(), cls.getName());
                    return ret.intValue();
                }
            }
        }

        return ret == null ? 0 : ret.intValue();
    }

    private static void bake() {
        if (!isDirty) return;
        FMLLog.fine("Forge RecipeSorter Baking:");
        DirectedGraph<SortEntry> sorter = new DirectedGraph<SortEntry>();
        sorter.addNode(before);
        sorter.addNode(after);
        sorter.addEdge(before, after);

        for (Map.Entry<String, SortEntry> entry : entries.entrySet()) {
            sorter.addNode(entry.getValue());
        }

        for (Map.Entry<String, SortEntry> e : entries.entrySet()) {
            SortEntry entry = e.getValue();
            boolean postAdded = false;

            sorter.addEdge(before, entry);
            for (String dep : entry.after) {
                if (entries.containsKey(dep)) {
                    sorter.addEdge(entries.get(dep), entry);
                }
            }

            for (String dep : entry.before) {
                postAdded = true;
                sorter.addEdge(entry, after);
                if (entries.containsKey(dep)) {
                    sorter.addEdge(entry, entries.get(dep));
                }
            }

            if (!postAdded) {
                sorter.addEdge(entry, after);
            }
        }

        List<SortEntry> sorted = TopologicalSort.topologicalSort(sorter);
        int x = sorted.size();
        for (SortEntry entry : sorted) {
            FMLLog.fine("  %d: %s", x, entry);
            priorities.put(entry.cls, x--);
        }
    }
}
