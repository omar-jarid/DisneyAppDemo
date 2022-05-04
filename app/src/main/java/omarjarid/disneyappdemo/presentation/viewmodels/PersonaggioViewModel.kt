package omarjarid.disneyappdemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import omarjarid.domain.model.Personaggio
import omarjarid.domain.repository.PersonaggioRepository
import javax.inject.Inject

@HiltViewModel
class PersonaggioViewModel @Inject constructor(
    private val repository: PersonaggioRepository
): ViewModel() {
    private val _personaggi = MutableStateFlow(emptyList<Personaggio>())
    val personaggi: StateFlow<List<Personaggio>> = _personaggi
    private var localPersonaggi: List<Personaggio> = emptyList()
    private var job: Job? = null
    fun loadPersonaggi() {
        viewModelScope.launch {
            val primiPersonaggi = repository.getAllCharacters()
            val totalPages = primiPersonaggi.last().totalPages
            val listaPersonaggi: MutableList<Personaggio> = emptyList<Personaggio>().toMutableList()
            for(i in 2..totalPages) {
                val listaTmp = repository.getAllCharacters(i)
                listaTmp.forEach { listaPersonaggi += it }
            }

            listaPersonaggi.sortBy { it.name }
            localPersonaggi = listaPersonaggi
            _personaggi.value = localPersonaggi
        }
    }

    fun search(text: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(300)
            _personaggi.value = localPersonaggi.filter { it.name.contains(text, ignoreCase = true) }
        }
    }


}