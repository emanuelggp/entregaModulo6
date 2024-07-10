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
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @ManyToOne
    @JoinColumn(name = "envio")
    private Usuario envio;

    @ManyToOne
    @JoinColumn(name = "recepcion")
    private Usuario recepcion;

    private int monto;
    private Date fecha;
}
