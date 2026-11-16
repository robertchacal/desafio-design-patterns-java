interface DescontoStrategy {
    double calcularDesconto(double valorOriginal);
}

class DescontoNatal implements DescontoStrategy {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * 0.20;
    }
}

class DescontoBlackFriday implements DescontoStrategy {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return valorOriginal * 0.50;
    }
}

class SemDesconto implements DescontoStrategy {
    @Override
    public double calcularDesconto(double valorOriginal) {
        return 0.0;
    }
}


class CalculadoraPreco {
    private DescontoStrategy estrategia;

  
    public void setEstrategia(DescontoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularPrecoFinal(double valorOriginal) {
        if (estrategia == null) {
            return valorOriginal;
        }
        double desconto = estrategia.calcularDesconto(valorOriginal);
        return valorOriginal - desconto;
    }
}


public class ExemploStrategy {
    public static void main(String[] args) {
        double precoProduto = 200.0;
        CalculadoraPreco carrinho = new CalculadoraPreco();

        System.out.println("=== TESTANDO PADRÃO STRATEGY ===");
        System.out.println("Valor original do produto: R$ " + precoProduto);
        System.out.println("-----------------------------------");

        
        carrinho.setEstrategia(new SemDesconto());
        System.out.println("Preço Normal: R$ " + carrinho.calcularPrecoFinal(precoProduto));

      
        carrinho.setEstrategia(new DescontoNatal());
        System.out.println("Preço no Natal (20% off): R$ " + carrinho.calcularPrecoFinal(precoProduto));

     
        carrinho.setEstrategia(new DescontoBlackFriday());
        System.out.println("Preço na Black Friday (50% off): R$ " + carrinho.calcularPrecoFinal(precoProduto));
    }
}