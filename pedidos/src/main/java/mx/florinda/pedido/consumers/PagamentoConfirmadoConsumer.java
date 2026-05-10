package mx.florinda.pedido.consumers;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import mx.florinda.pedido.Pedido;
import mx.florinda.pedido.StatusPedido;
import mx.florinda.pedido.events.PagamentoConfirmadoEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class PagamentoConfirmadoConsumer {

    @Incoming("pagamentosConfirmados")
    public Uni<Void> consume(PagamentoConfirmadoEvent evento) {
        return Panache.withTransaction(() -> {
            return Pedido.<Pedido>findById(evento.pedidoId)
                    .onItem().ifNotNull().invoke(pedido -> {
                       pedido.status = StatusPedido.PAGO;
                    });
        }).replaceWithVoid();
    }
}
