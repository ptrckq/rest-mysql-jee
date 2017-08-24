package br.com.ptrck.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.util.HttpResponseCodes;

import br.com.ptrck.model.Pessoa;

@Stateless
@Transactional
public class PessoaDAO {

	@PersistenceContext(unitName = "restapp")
	private EntityManager em;

	public ResponseBuilder adiciona(Pessoa p) {
		if (em.find(Pessoa.class, p.getRg()) != null){
			return Response.ok(HttpResponseCodes.SC_CONFLICT);
			
		} else {
			em.persist(p);
			return Response.ok(HttpResponseCodes.SC_OK);
		}
	}

	public ResponseBuilder altera(Pessoa p, String nome) {
		if (em.find(Pessoa.class, p.getRg()) != null) {
			em.merge(p);
			return Response.ok(HttpResponseCodes.SC_OK);
		} else {
			return Response.ok(HttpResponseCodes.SC_OK);
		}
	}

	public ResponseBuilder remove(Integer rg) {
		if (em.find(Pessoa.class, rg) != null) {
			em.remove(em.find(Pessoa.class, rg));
			return Response.ok(HttpResponseCodes.SC_OK);
		} else {
			System.out.println("Pessoa não encontrada");
			return Response.ok(HttpResponseCodes.SC_NOT_FOUND);
		}
	}
	
	public Pessoa buscaPessoa(Integer rg){
		if (em.find(Pessoa.class, rg) != null){
			return em.find(Pessoa.class, rg);
		} else {
			return null;
		}
		
	}
	

	public List<Pessoa> buscaTodos(){
		TypedQuery<Pessoa> pessoas = em.createNamedQuery("buscaTodos", Pessoa.class);
		List<Pessoa> listaDePessoas = pessoas.getResultList();
		return listaDePessoas;
		
	}

}
