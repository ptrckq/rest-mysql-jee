package br.com.ptrck.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.internal.NotNull;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name="buscaTodos", query = "select p from Pessoa p")
public class Pessoa {
	
	@NotNull
	@XmlElement @Size(min=3,max=40)
	private String nome;
	
	@NotNull
	@XmlElement
	@Range(message="Minimo de 0 max de 120") @Max(value=120) @Min(value=0)
	private Integer idade;
	
	
	@Id
	@XmlElement @NotNull
	@Min(100000000) @Max(999999999)
	private int rg;


	public Pessoa() {
	
	}

	public Pessoa(String nome, int idade, int rg) {
		this.rg = rg;
		this.nome = nome;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}

	
	

}
