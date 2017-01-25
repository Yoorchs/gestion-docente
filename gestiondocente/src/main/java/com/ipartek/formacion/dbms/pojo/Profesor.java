package com.ipartek.formacion.dbms.pojo;

public class Profesor extends Persona implements Comparable<Profesor>{
	public static final int CODIGO_NULO = -1;
	private int nSS;
	private int codigo;

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
	}

	public int getnSS() {
		return nSS;
	}

	public void setnSS(int nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.codigo + " "  + this.getNombre() + " " + this.getApellidos() + " " 
		+ this.getDireccion() + " " + this.getDni() + " " 
		+ this.getEmail() + " " + this.getnSS();
	}

	@Override
	public int compareTo(Profesor o) {
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}

}
