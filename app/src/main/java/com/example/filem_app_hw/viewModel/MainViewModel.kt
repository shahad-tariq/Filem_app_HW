package com.example.filem_app_hw.viewModel


import android.util.Log
import com.example.filem_app_hw.data.DetailsResponse
import androidx.lifecycle.*
import com.example.filem_app_hw.*
import com.example.filem_app_hw.adapter.ParentAdapter
import com.example.filem_app_hw.data.*
import com.example.filem_app_hw.data.dataAdapter.Categories
import com.example.filem_app_hw.data.dataAdapter.NestedDataObjectWrapper
import com.example.filem_app_hw.data.dataAdapter.NestedDataObjectWrapperVertical
import com.example.filem_app_hw.repository.Repository
import com.example.filem_app_hw.repository.Repository.getMoviesDetails
import com.example.filem_app_hw.repository.Repository.getMoviesSearch
import com.example.filem_app_hw.repository.Repository.getMoviesSimilar
import com.example.filem_app_hw.util.Constants
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_BIG_CARD
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_BIG_X_CARD
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_SMALL_CARD
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_VERY_SMALL_CARD
import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    var searchText = MutableLiveData<String>()

    var popularMovies = MutableLiveData<Status<PopularMoviesResponse?>>()
    var topMovies = MutableLiveData<Status<TopRatedResponse?>>()
    var tvAirMovies = MutableLiveData<Status<TvAirResponse?>>()
    var searchMovies = MutableLiveData<Status<SearchResponse?>>()
    var detailsMovies = MutableLiveData<Status<DetailsResponse?>>()
    var discoverMovies = MutableLiveData<Status<DiscoverResponse?>>()
    var similarMovies = MutableLiveData<Status<SimilarResponse?>>()

    var adapterMain = ParentAdapter()
    var adapterTv = ParentAdapter()
    var adapterTop = ParentAdapter()
    var adapterSearchList = ParentAdapter()
    var adapterSimilarMovies = ParentAdapter()
    var adapterDiscoversMovies = ParentAdapter()

    init {
        getResponseAndAddToAdapter()
        addActionToAdapter()

    }

    fun addActionToAdapter(){
        adapterMain.onClickAction()
        adapterTv.onClickAction()
        adapterTop.onClickAction()
        adapterSearchList.onClickAction()
        adapterSimilarMovies.onClickAction()
        adapterDiscoversMovies.onClickAction()
    }

    fun getResponseAndAddToAdapter(){
        viewModelScope.launch {
            Repository.apply {
                Collect(getMoviesPopular() , popularMovies)
                Collect(getMoviesTopRated() , topMovies)
                Collect(getMoviesTvAir() , tvAirMovies)
                Collect(getMoviesDiscover() , discoverMovies)
            }
            addToAdapterMain(mutableListOf() )
            addToAdapterTopMovies(mutableListOf())
            addToAdapterTv(mutableListOf() )
            addToAdapterDescoverMovies(mutableListOf())
        }
    }

    fun getAdapterSearch(){
        viewModelScope.launch {

             delay(2000)
             Repository.Collect(getMoviesSearch(searchText.value.toString()) , searchMovies)
             addToAdapterSearch(mutableListOf())
        }
    }

    fun getDetails(movieId:Int) {
        viewModelScope.launch {
            Repository.Collect(getMoviesDetails(movieId) , detailsMovies)
        }
    }

    fun getSimilar(movieId:Int) {
        viewModelScope.launch {
            Repository.Collect(getMoviesSimilar(movieId) , similarMovies)
            addToAdapterSimilarMovies(mutableListOf())
        }
    }



    fun addToAdapterMain(listAdapter: MutableList<HasType>) {
        listAdapter.apply {
            addToList(Categories("Population Movies"))
            addToList(NestedDataObjectWrapper(popularMovies.value?.toData()?.results, VIEW_TYPE_BIG_CARD))
            addToList(Categories("More Movies"))
            addToList(NestedDataObjectWrapperVertical(tvAirMovies.value?.toData()?.results, VIEW_TYPE_VERY_SMALL_CARD))
        }

        adapterMain.setData(dataList = listAdapter)
    }

    fun addToAdapterTopMovies(listAdapter: MutableList<HasType>) {
        listAdapter.apply {
            addToList(NestedDataObjectWrapper(topMovies.value?.toData()?.results, VIEW_TYPE_SMALL_CARD))
        }
        adapterTop.setData(dataList = listAdapter)
    }

    fun addToAdapterTv(listAdapter: MutableList<HasType>) {
            listAdapter.apply {
                addToList(Categories("Tv Movies"))
                addToList(NestedDataObjectWrapperVertical(tvAirMovies.value?.toData()?.results, VIEW_TYPE_SMALL_CARD))
            }
            adapterTv.setData(dataList = listAdapter)
    }

    fun addToAdapterSearch(listAdapter: MutableList<HasType>?) {
        if (!searchText.value?.toString().isNullOrEmpty()){
              listAdapter!!.addToList(NestedDataObjectWrapperVertical(searchMovies.value?.toData()?.results
                  , VIEW_TYPE_VERY_SMALL_CARD) )
              adapterSearchList.setData(listAdapter)
        }else{
            adapterSearchList.setData(dataList = emptyList())
        }

    }

    fun addToAdapterSimilarMovies(listAdapter: MutableList<HasType>) {
        listAdapter.apply {
            addToList(NestedDataObjectWrapper(similarMovies.value?.toData()?.results, VIEW_TYPE_BIG_CARD))
        }
        adapterSimilarMovies.setData(dataList = listAdapter)
    }

    fun addToAdapterDescoverMovies(listAdapter: MutableList<HasType>) {
        listAdapter.apply {
            addToList(Categories("Discover Movies"))
            addToList(NestedDataObjectWrapperVertical(discoverMovies.value?.toData()?.results, VIEW_TYPE_VERY_SMALL_CARD))
        }
        adapterDiscoversMovies.setData(dataList = listAdapter)
    }


}










