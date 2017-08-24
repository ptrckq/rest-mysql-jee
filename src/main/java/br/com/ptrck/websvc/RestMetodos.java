package br.com.ptrck.websvc;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.util.HttpResponseCodes;

import br.com.ptrck.dao.PessoaDAO;
import br.com.ptrck.model.Pessoa;

@Path("pessoa")
public class RestMetodos {

	@Inject
	private PessoaDAO pessoaDao;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	@Path("dados/{rg}/{formato}")
	public Response retornaDadosEmXMLouJSON(@PathParam("formato") String formato, @PathParam("rg") Integer rg) {

		Pessoa pessoa = pessoaDao.buscaPessoa(rg);

		if (formato == null || !formato.equals("json")) {
			System.out.println("formato nulo ou inválido, alterando para XML");
			formato = "xml";
		}
		if (pessoa == null) {
			pessoa = new Pessoa();
			return Response.ok(HttpResponseCodes.SC_NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
					.build();
		}
		return Response.ok(pessoa).header(HttpHeaders.CONTENT_TYPE,
				formato.equals("json") ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML).build();

	}

	
	@POST
	@Path("/dados")
	public Response adicionaNovaPessoa(@QueryParam("nome") String nome, @QueryParam("idade") Integer idade,
			@QueryParam("rg") Integer rg) {
		Pessoa pessoa = new Pessoa(nome, idade, rg);
		ResponseBuilder responseBuilder = pessoaDao.adiciona(pessoa);
		return responseBuilder.build();

	}

	@PUT
	@Path("/dados")
	public Response alteraPessoa(@QueryParam("nome") String nome, @QueryParam("idade") Integer idade,
			@QueryParam("rg") Integer rg) {
		Pessoa p = new Pessoa(nome, idade, rg);
		pessoaDao.altera(p, nome);
		return Response.ok(HttpResponseCodes.SC_OK).build();
	}

	@DELETE
	@Path("/dados")
	public Response deletaPessoa(@QueryParam("rg") Integer rg) {
		ResponseBuilder s = pessoaDao.remove(rg);
		return s.build();
	}

	@GET
	@Path("/dados/todos")
	@Produces(MediaType.APPLICATION_XML)
	public Response buscaTodos() {
		List<Pessoa> lista = pessoaDao.buscaTodos();
		GenericEntity<List<Pessoa>> entidade = new GenericEntity<List<Pessoa>>(lista) {
		};
		return Response.ok(entidade).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();
	}
		
}
