import java.util.Objects;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Pais.java
//  @ Date : 23/10/2022
//  @ Author : 
//
//




public class Pais {
	private String nombre;
	private int ranking;

	
	public Pais(String nombre, int ranking) {
		this.nombre = nombre;
		this.ranking = ranking;
	}


	public int verRanking() {
	
	}
	
	public String verNombre() {
	
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, ranking);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
