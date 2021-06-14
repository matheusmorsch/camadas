package br.org.serratec.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public EnderecoDTO buscar(String cep) {
		Optional<Endereco>endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		}else {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco>enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if(enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserir(enderecoViaCep.get());
			}else {
				return null;
			}
		}
	}
	
	
	public EnderecoDTO inserir (Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return new EnderecoDTO(endereco);
	}

}
