package br.com.wellinton.cotacao.controller;
/*
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DTO.FiltroDTO;
import DTO.IndicadorDTO;
import entity.Cotacoes;
import entity.Indicadores;
import service.CotacoesService;
import service.IndicadoresService;


@Named
@ViewScoped
public class CotacoesControllerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CotacoesService cotacoesService;
    
    @Inject
    private IndicadoresService indicadorService;
    
    private Cotacoes selectedCotacao;
    
    private Indicadores selectedIndicador;

    private String termoPesquisa;
    
    private String selectedAPI;
    private String selectedFilter;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private Long indicadorId;
    private String indicadorDescription;
    
    private String startDate;
    private String finalDate;
    
    private List<Cotacoes> listaCotacoes;
    private List<Indicadores> listaIndicadores;
    private List<IndicadorDTO> indicadoresFiltrados;
    private List<FiltroDTO> cotacoesFiltradas = new ArrayList<>();
    
    StringBuilder builder = new StringBuilder();
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @PostConstruct
    public void init() {
    	 Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    	 selectedCotacao = (Cotacoes) flash.get("selectedCotacao");
	      
    	 
    	 if (selectedCotacao == null) {
     	 selectedCotacao = new Cotacoes();
    	 }
    	 
    }
    
    public String editar(Cotacoes cotacoes) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("selectedCotacao", cotacoes);
        
        return "Cotacoes?faces-redirect=true";
    }
    
    public void initNewCotacao() {
        selectedCotacao = new Cotacoes();
        listaIndicadores = indicadorService.todosIndicadores();
    }
    
    public void initNewFiltro() {
    	selectedFilter = "";
    	dataInicial = null;
    	dataFinal = null;
    	indicadorId = null;
    }
    
    public void initNewBuscaAPI() {
    	selectedIndicador = new Indicadores();
    	selectedAPI = null;
    	dataInicial = null;
    	dataFinal = null;
    	listaIndicadores = indicadorService.todosIndicadores();
    }
    
    public static String formatLocalDateTime(LocalDateTime dateTime) {
    	return dateTime.format(FORMATTER);
    }
    
    public void todosIndicadores() {
        listaIndicadores = indicadorService.todosIndicadores();
    }

    public void todasCotacoes() {
        listaCotacoes = cotacoesService.todasCotacoes();
    }
     
    public void pesquisa() {
        listaCotacoes = cotacoesService.buscar(termoPesquisa);
    }
    
    public String salvar() {
        if (selectedCotacao.getIndicadores() != null) {
            cotacoesService.salvar(selectedCotacao);
            listaCotacoes = cotacoesService.todasCotacoes();
            
            selectedCotacao = new Cotacoes();
            
        }
        return "ListaDeCotacoes?faces-redirect=true";
    }

    public void excluir() {
        if (selectedCotacao != null) {
            cotacoesService.excluir(selectedCotacao);
            listaCotacoes.remove(selectedCotacao);
            
            selectedCotacao = new Cotacoes();
            
            FacesContext.getCurrentInstance().addMessage(null,
 	        		new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cotação excluída com sucesso!"));
        }else {
        	FacesContext.getCurrentInstance().addMessage(null,
 	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha", "Cotação não foi excluída"));
        }
    }
    
    
    public List<IndicadorDTO> getListaIndicadoresFiltrados() {
    	if (indicadoresFiltrados == null) {
    		indicadoresFiltrados = cotacoesService.porIndicador();
    	}
    	
    	
    	return indicadoresFiltrados;
    }
    

    public List<Indicadores> getListaIndicadores() {
        if (listaIndicadores == null) {
            listaIndicadores = indicadorService.todosIndicadores();
        }
        return listaIndicadores;
    }

    public List<Cotacoes> getListaCotacoes() {
        if (listaCotacoes == null) {
            listaCotacoes = cotacoesService.todasCotacoes();
        }
        return listaCotacoes;
    }
    
    public Integer diferenca(LocalDateTime dataInicial, LocalDateTime dataFinal) {
    	if(dataInicial != null && dataInicial != null) {
    		
    		long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
    		return (int) dias;
    	}
    	
    	return null;
    }
    
    public void buscarAPI(String indicadorDescription, String selectedAPI, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        
    	Integer dias = diferenca(dataInicial, dataFinal);
    	switch (selectedAPI) {
    	case "1":
    		chamarAcoes(indicadorDescription);
    		break;
    	case "2":
    		chamarMoedas(indicadorDescription, dias);
    		break;
    	}
    }
    
    public void chamarAcoes(String indicadorDescription) {
    	cotacoesService.atualizarAcoes(indicadorDescription);
    }
    
    public void chamarMoedas(String indicadorDescription, Integer dias) {
    	cotacoesService.atualizarMoedas(indicadorDescription, dias);
    }
    
    public List<FiltroDTO> getListaCotacoesFiltradas() {
    	if(cotacoesFiltradas == null) {
    		cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(dataInicial, dataFinal, indicadorId);
    	}
    	return cotacoesFiltradas;
    }
    
    
    public void aplicarFiltro() {
        switch (selectedFilter) {
            case "1":
                filtro1Dia();
                break;
            case "3":
                filtro3Dias();
                break;
            case "5":
                filtro5Dias();
                break;
            case "10":
                filtro10Dias();
                break;
            case "15":
                filtro15Dias();
                break;
            case "30":
                filtro30Dias();
                break;
            case "custom":
                filtroCustom();
                break;
            default:
                
        }
    }
    
    
    public void filtro1Dia() {
    	cotacoesFiltradas =  cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 1), LocalDateTime.now(), indicadorId );
    }
    
    public void filtro3Dias() {
    	cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 3), LocalDateTime.now(), indicadorId );
    }
    
    public void filtro5Dias() {
    	cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 5), LocalDateTime.now(), indicadorId);
    }
    
    public void filtro10Dias() {
    	cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 10), LocalDateTime.now(), indicadorId);
    }
    
    
    public void filtro15Dias() {
    	cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 15), LocalDateTime.now(), indicadorId);
    }
    
    public void filtro30Dias() {
    	cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(getDataAnterior(LocalDateTime.now(), 30), LocalDateTime.now(), indicadorId);
    	System.out.println(cotacoesFiltradas);
    }
    
    public void filtroCustom() {
    	 cotacoesFiltradas = cotacoesService.buscarPorPeriodoEIndicador(dataInicial, dataFinal, indicadorId);
    }
    
    
    public String gerarDadosGrafico() {
        StringBuilder builder = new StringBuilder();

        if (cotacoesFiltradas != null && !cotacoesFiltradas.isEmpty()) {
            builder.append("[['Data', 'Legenda', { role: 'tooltip', p: { html: true } }],");

            Locale locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

            for (FiltroDTO cotacao : cotacoesFiltradas) {
            	
                String formattedValue = currencyFormat.format(cotacao.getValor());
                String formattedDate = cotacao.getDataHora().format(dateFormatter); 
                
                String tooltipContent = "<div id='tooltip'>" + 
                	    formattedDate + " " +
                	    formattedValue.replace("'", "\\'") + 
                	    "</div>";
                                        
                builder.append("['")
                       .append(formattedDate) 
                       .append("', ")
                       .append(cotacao.getValor()) 
                       .append(", '")
                       .append(tooltipContent.replace("'", "\\'"))
                       .append("'], ");
            }

            builder.setLength(builder.length() - 2); 
            builder.append("]"); 
        }

        System.out.println(builder.toString()); 
        return builder.toString();
    }
    
    public static LocalDateTime getDataAnterior(LocalDateTime dataBase, int dias) {
    	return dataBase.minusDays(dias);
    }
    
	public Cotacoes getSelectedCotacao() {
        return selectedCotacao;
    }

    public void setSelectedCotacao(Cotacoes selectedCotacao) {
        this.selectedCotacao = selectedCotacao;
    }

    public boolean isCotacaoSeleciona() {
        return selectedCotacao != null && selectedCotacao.getId() != null;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
    
    
    public String getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(String selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Long getIndicadorId() {
		return indicadorId;
	}

	public void setIndicadorId(Long indicadorId) {
		this.indicadorId = indicadorId;
	}

	public String getIndicadorDescription() {
		return indicadorDescription;
	}

	public void setIndicadorDescription(String indicadorDescription) {
		this.indicadorDescription = indicadorDescription;
	}

	public String getSelectedAPI() {
		return selectedAPI;
	}

	public void setSelectedAPI(String selectedAPI) {
		this.selectedAPI = selectedAPI;
	}

	public Indicadores getSelectedIndicador() {
		return selectedIndicador;
	}

	public void setSelectedIndicador(Indicadores selectedIndicador) {
		this.selectedIndicador = selectedIndicador;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}
	
}
    

*/