package com.tomtom.tom.tmdb.ui.list


import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tomtom.tom.domain.model.Movie
import com.tomtom.tom.tmdb.R
import com.tomtom.tom.tmdb.adapters.CustomGridLayoutManager
import com.tomtom.tom.tmdb.adapters.MoviesListAdapter
import com.tomtom.tom.tmdb.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_content.*

class MoviesListFragment : BaseFragment(), MoviesListContract.View {

    var isLoading = false

    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: MoviesListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val presenter: MoviesListContract.Presenter = MoviesListPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view!!)

        presenter.onViewCreated()

        sort_button.setOnClickListener {
            presenter.onSortButtonClick()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (!isLoading && totalItemCount <= lastVisibleItem + 3) {
                    isLoading = true
                    presenter.downloadNextPage()
                }
            }
        })
    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.movies_recycler)
        adapter = MoviesListAdapter(emptyList(), presenter)
        layoutManager = CustomGridLayoutManager(view.context, 2, 1f)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onDataUpdate(movies: List<Movie>) {
        isLoading = false
        activity.runOnUiThread {
            adapter.updateList(movies)
        }
    }

    override fun setSortingButtonState(active: Boolean) {
        sort_button.text = if (active) resources.getString(R.string.show_unsorted) else resources.getString(R.string.sort_by_date)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}