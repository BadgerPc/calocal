package vajracode.calocal.server.dto;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vajracode.calocal.shared.model.HasId;

@MappedSuperclass
public class DTO implements Serializable, HasId {
		
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="BIGINT(20) UNSIGNED")
	//@org.springframework.data.annotation.Id
	//@Field(type = FieldType.String)
	private long id;
	
	public DTO() {	
	}
	
	public static long getIdDirect(DTO e) {
		if (e instanceof HibernateProxy) {
			LazyInitializer lazyInitializer = ((HibernateProxy) e).getHibernateLazyInitializer();
		    if (lazyInitializer.isUninitialized()) {
		    	return (Long) lazyInitializer.getIdentifier();
		    }
		}
		return e.getId();
	}
	
	public long getIdDirect() {
		return DTO.getIdDirect(this);
	}

	@Override
	public long getId() {		
		return id;
	}
	
	public void setId(long id) {
		this.id = id;	
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj.getClass().equals(this.getClass()) && getId() == ((DTO)obj).getId();
	}

	@JsonIgnore
	public String getDebugInfo() {		
		return getClass().getSimpleName() + "(" + id + ")";
	}
		
}
