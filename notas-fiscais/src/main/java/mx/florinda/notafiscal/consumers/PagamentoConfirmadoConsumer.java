package mx.florinda.notafiscal.consumers;

import io.smallrye.mutiny.Uni;
import mx.florinda.notafiscal.PedidoService;
import mx.florinda.notafiscal.events.PagamentoConfirmadoEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class PagamentoConfirmadoConsumer {
    @RestClient
    PedidoService pedidoService;

    @Incoming("pagamentosConfirmados")
    public Uni<Void> consume(PagamentoConfirmadoEvent event) {
        Uni<String> notaFiscal = pedidoService.notaFiscal(event.pedidoId, event.valor);
        return notaFiscal.onItem().invoke(nf -> {
            System.out.println(nf);
        }).replaceWithVoid();
    }
}
