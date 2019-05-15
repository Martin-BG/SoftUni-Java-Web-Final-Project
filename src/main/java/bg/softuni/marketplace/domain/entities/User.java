package bg.softuni.marketplace.domain.entities;

import bg.softuni.marketplace.domain.api.Viewable;
import bg.softuni.marketplace.domain.validation.annotations.composite.user.ValidUserAuthorities;
import bg.softuni.marketplace.domain.validation.annotations.composite.user.ValidUserEncryptedPassword;
import bg.softuni.marketplace.domain.validation.annotations.composite.user.ValidUserUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_username", columnNames = {"username"})
        }
)
@NamedQuery(
        name = "User.findUserEager",
        query = "SELECT u FROM User u LEFT JOIN FETCH u.authorities AS a WHERE u.username = :username",
        hints = {
                @QueryHint(name = QueryHints.HINT_READONLY, value = "true")
        }
)
public class User extends BaseUuidEntity implements UserDetails, Viewable<User> {

    private static final long serialVersionUID = 1L;

    @ValidUserUsername
    @Column(nullable = false, updatable = false, length = ValidUserUsername.MAX_LENGTH)
    private String username;

    @ValidUserEncryptedPassword
    @Column(nullable = false, length = ValidUserEncryptedPassword.MAX_LENGTH)
    private String password;

    @ValidUserAuthorities
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users_roles_users"))},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_users_roles_roles"))})
    private Set<Role> authorities = new HashSet<>();

    private boolean active;

    public User(String username,
                String password,
                Collection<? extends Role> authorities,
                boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities.addAll(authorities);
        active = isActive;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
