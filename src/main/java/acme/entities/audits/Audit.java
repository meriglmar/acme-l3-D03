
package acme.entities.audits;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.auditingRecords.TypeMark;
import acme.entities.courses.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Auditor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 100)
	protected String			conclusion;

	@NotBlank
	@Length(max = 100)
	protected String			strongPoints;

	@NotBlank
	@Length(max = 100)
	protected String			weakPoints;

	protected boolean			draftMode;


	// Derived attributes -----------------------------------------------------
	//una nota (computada como la moda de las notas en los registros de auditoría correspondientes; empates debe romperse arbitrariamente si es necesario).
	@Transient
	public TypeMark mark(final Collection<TypeMark> lista) {
		final Map<TypeMark, Integer> frecuencias = new HashMap<>();
		for (final TypeMark type : lista)
			frecuencias.put(type, frecuencias.getOrDefault(type, 0) + 1);

		final List<TypeMark> moda = new ArrayList<>();
		int maxFrecuencia = 0;
		for (final Map.Entry<TypeMark, Integer> entry : frecuencias.entrySet())
			if (entry.getValue() > maxFrecuencia) {
				moda.clear();
				moda.add(entry.getKey());
				maxFrecuencia = entry.getValue();
			} else if (entry.getValue() == maxFrecuencia)
				moda.add(entry.getKey());

		return moda.get((int) (Math.random() * moda.size()));
	}


	// Relationships ----------------------------------------------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Auditor auditor;

}
