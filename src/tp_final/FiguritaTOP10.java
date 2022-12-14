package tp_final;

import java.util.Objects;

public class FiguritaTOP10 extends FiguritaTradicional {
	private String mundial;
	private String balonGanado;
	
	
	
	public FiguritaTOP10(Pais paisJugador, double valorBase, String nombreJugador, 
			int numJugador, String mundial, String balonGanado) {
		super(paisJugador, valorBase, nombreJugador, numJugador);
		this.mundial = mundial;
		this.balonGanado = balonGanado;
	}



	@Override
	public double calcularPrecio() {
		double aumentoDePrecio = balonGanado.equals("Oro") ?
				20/100 : 10/100;
		
		return super.calcularPrecio() + super.calcularPrecio()*aumentoDePrecio;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(balonGanado, mundial);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FiguritaTOP10 other = (FiguritaTOP10) obj;
		return this.getNumeroIdentificador() == other.getNumeroIdentificador();
	}
}
