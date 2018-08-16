package br.com.Falcao.CRUD.repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.Falcao.CRUD.models.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{

	Pessoa findByCodigo(long codigo);
	
}
