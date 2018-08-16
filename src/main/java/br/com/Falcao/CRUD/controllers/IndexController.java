package br.com.Falcao.CRUD.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.Falcao.CRUD.models.Pessoa;
import br.com.Falcao.CRUD.repositorys.PessoaRepository;

@Controller
public class IndexController {

	/* Navegação */
	
		/*@RequestMapping(path="/", method=RequestMethod.GET)
		public String index() {
			return "index";
		}*/
		
		@RequestMapping(path="/cadastrarPessoa", method=RequestMethod.GET)
		public String areaCadastrarPessoa() {
			return "areaCadastrarPessoa";
		}
	
	/* Navegação */
	
	/* Funcionalidades */
		
		@Autowired //Faz uma injeção das dependência do classe PessoaRepository
		private PessoaRepository pessoaRe;
		
		/* Cadastrar Pessoa */
		
			@RequestMapping(path="/proCadastrarPessoa", method=RequestMethod.POST)
			public String cadastroPessoa(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
				
				if(result.hasErrors()) {
					attributes.addFlashAttribute("mensagem", "Verifique os campos!");
					return "redirect:/cadastrarPessoa";
				}
								
				pessoaRe.save(pessoa);
				attributes.addFlashAttribute("mensagem", "Pessoa Cadastrada com Sucesso!");
				
				return "redirect:/";
			}
		
		/* Cadastrar Pessoa */
			
		/* AtualizarPessoa */
			
			
			
		/* AtualizarPessoa */
			
		/* Apagar Pessoa */
			
			@RequestMapping(path="/deletarPessoa")
			public String deletarPessoa(long codigo, RedirectAttributes attributes){
				
				Pessoa pessoa = pessoaRe.findByCodigo(codigo);
				pessoaRe.delete(pessoa);
				
				attributes.addFlashAttribute("mensagem", "Pessoa deletada com sucesso!");
				
				return "redirect:/";
			}
			
		/* Apagar Pessoa */
			
			/* View */
			
				@RequestMapping(path="/", method=RequestMethod.GET)
				public ModelAndView index() {
					
					ModelAndView mv= new ModelAndView("index");
					Iterable<Pessoa>pessoas = pessoaRe.findAll();
					mv.addObject("pessoas", pessoas);
					
					return mv;
				}
				
				@RequestMapping(path="/exibir/{codigo}")
				public ModelAndView detalhesPessoa(@PathVariable("codigo") long codigo) {
					
					Pessoa pessoa = pessoaRe.findByCodigo(codigo);
					
					ModelAndView mv= new ModelAndView("areaExibirInformacoes");
					mv.addObject("pessoa", pessoa);
					
					System.out.println("pessoa" + pessoa);
					
					return mv;
				}
				
				@RequestMapping(path="/atualizar/{codigo}")
				public ModelAndView atualizarPessoa(@PathVariable("codigo") long codigo, @Valid Pessoa updatePessoa, BindingResult result, RedirectAttributes attributes) {
					
					Pessoa pessoa = pessoaRe.findByCodigo(codigo);
					
					ModelAndView mv= new ModelAndView("areaAtualizarPessoa");
					mv.addObject("pessoa", pessoa);
					
					System.out.println("pessoa" + pessoa);
					
					attributes.addFlashAttribute("mensagem", "Informações atualizadas com sucesso!");
					
					return mv;
				}
				
				@RequestMapping(path="/atualizar/{codigo}", method=RequestMethod.POST)
				public String proAtualizarPessoa(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
					
					if(result.hasErrors()) {
						attributes.addFlashAttribute("mensagem", "Verifique os campos!");
						return "redirect:/cadastrarPessoa";
					}
									
					pessoaRe.save(pessoa);
					
					attributes.addFlashAttribute("mensagem", "Informações atualizadas com sucesso!");
					
					return "redirect:/";
				}
				
			/* View */
			
	/* Funcionalidades */
		
}
