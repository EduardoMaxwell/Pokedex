package com.eduardomaxwell.pokedex.api

/*
class PokemonPagingSource(val repository: PokemonRepository) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_NUMBER
            val response = repository.listPokemons(nextPage)
            var nextPageNumber: Int? = null

            if (response?.next != null) {
                val uri = Uri.parse(response.next)
                val nextPageQuery = uri.getQueryParameters("limit")
                nextPageNumber = nextPageQuery.toString().toInt()
            }
            LoadResult.Page(
                data = response!!.results,
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_NUMBER = 1
    }
}*/