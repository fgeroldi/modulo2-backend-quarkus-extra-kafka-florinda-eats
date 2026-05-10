package mx.florinda.pedido.events;

public class PagamentoConfirmadoEvent {
    public Long pagamentoId;
    public Long pedidoId;

    @Override
    public String toString() {
        return "PagamentoConfirmadoEvent{" +
                "pagamentoId=" + pagamentoId +
                ", pedidoId=" + pedidoId +
                '}';
    }
}
