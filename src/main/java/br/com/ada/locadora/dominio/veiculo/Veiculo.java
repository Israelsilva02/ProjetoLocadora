package br.com.ada.locadora.dominio.veiculo;
public class Veiculo {

    private final VeiculoID placa;
    private String marca;
    private TipoVeiculo tipoVeiculo;
    private boolean disponivel;

    private Veiculo(VeiculoID placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.tipoVeiculo = tipoVeiculo;
        this.disponivel = true;
    }

    public static Veiculo criar(VeiculoID placa, String marca, TipoVeiculo tipoVeiculo) {
        return new Veiculo(placa, marca, tipoVeiculo);
    }

    public void reservar(){
        if (isDisponivel()) {
            this.disponivel = false;
        } else {
            throw new RuntimeException("Veículo já reservado");
        }
    }

    public void liberar(){
        this.disponivel = true;
    }

    public VeiculoID getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa=" + placa +
                ", marca='" + marca + '\'' +
                ", tipoVeiculo=" + tipoVeiculo +
                ", disponivel=" + disponivel +
                '}';
    }
}

