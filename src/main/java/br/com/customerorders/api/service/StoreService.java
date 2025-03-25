package br.com.customerorders.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.customerorders.api.domain.entity.Store;
import br.com.customerorders.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.customerorders.api.domain.exception.RegraNegocioException;
import br.com.customerorders.api.repository.StoreRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;
	
	public Store buscar(Long storeId) {
		return storeRepository.findById(storeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Loja não encontrada"));
	}
	

	@Transactional
	public Store salvar(Store newStore) {
		
		// Checar se já não existe loja com o nome informado
		boolean storeNameEmUso = storeRepository.findByStoreName(newStore.getStoreName())
				.filter(p -> !p.equals(newStore)).isPresent();

		if (storeNameEmUso) {
			throw new RegraNegocioException("Já existe uma loja cadastrada com este nome");
		}
		
		// Ao menos um endereço devem estar preenchidos
		
		if(!isValid(newStore))
		{
			throw new RegraNegocioException("Ao menos um dos campos de endereço devem ser preenchidos");
		}		

		return storeRepository.save(newStore);

	}
	
	boolean isValid(Store newStore) {
		return (
					(newStore.getPhysicalAddress() != null && !newStore.getPhysicalAddress().trim().isEmpty())
					||
					(newStore.getWebAddress() != null && !newStore.getWebAddress().trim().isEmpty())
				);
	}
	
	@Transactional
	public void excluir(Long storeId) {
		storeRepository.deleteById(storeId);
	}

	
	

}
