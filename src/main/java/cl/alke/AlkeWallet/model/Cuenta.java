package cl.alke.AlkeWallet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    private int numeroCuenta;
    private int saldo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}
