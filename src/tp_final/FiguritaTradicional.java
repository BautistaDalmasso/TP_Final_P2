package tp_final;

import java.util.Objects;

public class FiguritaTradicional {
	private Pais paisJugador;
	
	private int numeroIdentificador;
	private double valorBase;
	private String nombreJugador;
	private int numJugador;
	
	
	public FiguritaTradicional(Pais paisJugador, double valorBase, String nombreJugador, 
			int numJugador) {
		this.paisJugador = paisJugador;
		this.valorBase = valorBase;
		this.nombreJugador = nombreJugador;
		this.numJugador = numJugador;
		
		numeroIdentificador = hashCode();
	}


	public double calcularPrecio() {
		return paisJugador.verRanking() + valorBase;
	}


	public int getNumeroIdentificador() {
		return numeroIdentificador;
	}
	
	public String getNombrePais() {
		return paisJugador.verNombre();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombreJugador, paisJugador, valorBase);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FiguritaTradicional other = (FiguritaTradicional) obj;
		
		return this.numeroIdentificador == other.numeroIdentificador;
	}
	
	
	@Override
	public String toString() {
		return paisJugador.verNombre() + "-" + numJugador;
	}


	public int verNumJugador() {
		return numJugador;
	}
	
	
}
