package bg.softuni.marketplace.domain.models.binding.user;

import bg.softuni.marketplace.domain.api.Bindable;
import bg.softuni.marketplace.domain.entities.User;
import bg.softuni.marketplace.domain.enums.Authority;
import bg.softuni.marketplace.domain.validation.annotations.composite.user.ValidUserAuthority;
import bg.softuni.marketplace.domain.validation.annotations.composite.user.ValidUserUsername;
import bg.softuni.marketplace.domain.validation.annotations.spel.SpELAssert;
import bg.softuni.marketplace.domain.validation.groups.GroupOne;
import bg.softuni.marketplace.domain.validation.groups.GroupTwo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = {"username"})
@NoArgsConstructor
public class UserRoleBindingModel implements Bindable<User>, Serializable {

    private static final long serialVersionUID = 1L;

    @ValidUserUsername
    @SpELAssert(message = "{user.update-role.username.not-found}", groups = GroupOne.class,
            value = "@userRepository.hasUsername(#this)")
    @SpELAssert(message = "{user.update-role.username.is-root}", groups = GroupTwo.class,
            value = "not @userRepository.isRoot(#this)")
    private String username;

    @ValidUserAuthority
    @SpELAssert(message = "{user.update-role.authority.is-root}", groups = GroupOne.class,
            value = "#this != T(bg.softuni.marketplace.domain.enums.Authority).ROOT")
    private Authority authority;
}
