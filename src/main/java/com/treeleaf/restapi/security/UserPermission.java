package com.treeleaf.restapi.security;
public enum UserPermission {
        USER_READ("User:Read"),
        USER_WRITE("User:Write"),
        USER_UPDATE("User:Update"),
        USER_DELETE("User:Delete");

        private final String permission;

        UserPermission(String permission){
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }
}
