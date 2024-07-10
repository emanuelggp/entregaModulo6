package cl.alke.AlkeWallet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "envio")
    private Usuario envio;

    @ManyToOne
    @JoinColumn(name = "recepcion")
    private Usuario recepcion;

    private double monto;
    private Date fecha;
}
