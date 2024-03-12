package br.com.ada.locadora.domain.veiculo;

public class Veiculo {
    private String placa;
    private String marca;
    private TipoVeiculo tipoVeiculo;
    private boolean disponivel;

    public Veiculo(String placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.tipoVeiculo = tipoVeiculo;
    }

    public void reservar(){
        if (consultarDisponibilidade()) {
            this.disponivel = false;

        }else {
            System.out.println("Veiculo não disponivel!!");
        }

    }
    public void liberar(){
        this.disponivel = true;
    }

    public boolean consultarDisponibilidade() {
        return disponivel;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public void alterarPlaca(String placa) {
        this.placa = placa;
    }

    public void alterarMarca(String marca) {
        this.marca = marca;
    }

    public void alterarTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
}
