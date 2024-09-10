package vn.project.shopapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    private Boolean active;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "facebook_account_id")
    private LocalDate facebookAccountId;

    @Column(name = "google_account_id")
    private LocalDate googleAccountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
