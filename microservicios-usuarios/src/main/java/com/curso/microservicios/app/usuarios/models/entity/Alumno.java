package com.curso.microservicios.app.usuarios.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Hace referencia que es una entidad relacional, tabla de la BD
@Entity
/*
 * name = nombre de la tabla (opcional)
 * si se omite el nombre de la tabla es igual al nombre de la clase en singular
 * depende del motor el nombre de "Table" se escriben en mayusculas o minusculas
 * los nombres compuesto se separan con guion bajo " _ " mientras que las clases
 * con mayusculas, las clases son en singular y las tablas en plural. 
 * */
@Table(name="alumnos")
public class Alumno {
	@Id //Indica que es el identificador unico de la tabla
	/*
	 * MYSQL = IDENTITY
	 * ORACLE = SECUENCE 
	 * Indica que es auto incrementable
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String nombre;
	private String apellido;
	private String email;
	
	@Column(name = "create_at") //En la tabla se separa con guion bajo
	@Temporal(TemporalType.TIMESTAMP) //Indica que nuestra fecha sea completa un TimeStamp
	private Date createAt;
	
	//Indica que ejecuta un metodo automaticamente antes de persistir con la base de Datos (insert)
	@PrePersist 
	public void prePersist()
	{
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}
