package services;

import entities.AppUser;

/**
 * Created by Rustem.
 */
public class Session {
    private static AppUser currentAppUser;
    private final static String ROLE_CONTENTMANAGER = "ROLE_CONTENTMANAGER";
    private final static String ROLE_MANAGER = "ROLE_MANAGER";

    public static AppUser getCurrentAppUser() {
        return currentAppUser;
    }

    public static void setCurrentAppUser(AppUser currentAppUser) {
        Session.currentAppUser = currentAppUser;
    }

    public static Boolean hasCurrentAppUserEditPermissions() {
        return currentAppUser != null &&
                (ROLE_CONTENTMANAGER.equals(currentAppUser.getAppRole().getName()) || ROLE_MANAGER.equals(currentAppUser.getAppRole().getName()));
    }
}
