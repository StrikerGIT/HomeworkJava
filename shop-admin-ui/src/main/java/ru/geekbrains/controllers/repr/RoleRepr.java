package ru.geekbrains.controllers.repr;


import ru.geekbrains.model.Role;
import ru.geekbrains.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class RoleRepr {

        private Long id;

        @NotEmpty
        private String name;

        private Set<User> users;


        public RoleRepr() {
        }

        public RoleRepr(Role role) {
            this.id = role.getId();
            this.name = role.getName();
            this.users = role.getUsers();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String username) {
            this.name = username;
        }

        public Set<User> getUsers() {
            return users;
        }

        public void setUsers(Set<User> users) {
            this.users = users;
        }

        @Override
            public String toString() {
                return "RoleRepr{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

}
