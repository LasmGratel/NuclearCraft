package cofh.api.tileentity;

import net.minecraft.entity.player.EntityPlayer;

import com.mojang.authlib.GameProfile;

/**
 * Implement this interface on Tile Entities which can have access restrictions.
 *
 * @author King Lemming
 *
 */
public interface ISecurable {

    /**
     * Enum for Access Modes - Restricted is Friends Only, Private is Owner only.
     *
     * @author King Lemming
     *
     */
    public static enum AccessMode {

        PUBLIC,
        RESTRICTED,
        PRIVATE;

        public boolean isPublic() {

            return this == PUBLIC;
        }

        public boolean isRestricted() {

            return this == RESTRICTED;
        }

        public boolean isPrivate() {

            return this == PRIVATE;
        }

        public static AccessMode stepForward(AccessMode curAccess) {

            return curAccess == PUBLIC ? RESTRICTED : curAccess == PRIVATE ? PUBLIC : PRIVATE;
        }

        public static AccessMode stepBackward(AccessMode curAccess) {

            return curAccess == PUBLIC ? PRIVATE : curAccess == PRIVATE ? RESTRICTED : PUBLIC;
        }
    }

    boolean setAccess(AccessMode access);

    boolean setOwnerName(String name);

    boolean setOwner(GameProfile name);

    AccessMode getAccess();

    String getOwnerName();

    GameProfile getOwner();

    boolean canPlayerAccess(EntityPlayer player);

}
