package ioc.dependecy.bean.definition.factory;

import ioc.dependecy.domain.User;

public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
