package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    //https://stackoverflow.com/questions/52811685/kotlin-does-not-understand-viewmodelproviders-ofactivity-fragment
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //TODO to use for selecting dates/weekly/... options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}

/*
class MainFragment : Fragment() {
    private val TAG: String = javaClass.simpleName

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            MainViewModelFactory(AsteroidRadarDatabase.getInstance(requireContext()))
        )[MainViewModel::class.java]
    }

    private val asteroidAdapter = AsteroidAdapter(AsteroidAdapter.AsteroidOnClickListener {
        Log.d(TAG, "Item clicked: $it")
        findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.asteroidRecycler.adapter = asteroidAdapter

        setHasOptionsMenu(true)

        // initialize observers
        viewModel.pictureOfDay.observe(viewLifecycleOwner) { setPictureOfDay(it) }
        viewModel.asteroids.observe(viewLifecycleOwner) {
            Log.d(TAG, "Number of observed items: ${it?.count()}")
            asteroidAdapter.submitList(it)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "Menu option $item clicked")

        when (item.itemId) {
            R.id.show_today_menu -> viewModel.onShowTodayAsteroids()
            R.id.show_week_menu -> viewModel.onShowWeekAsteroids()
            R.id.show_all_menu -> viewModel.onShowAllAsteroids()
        }

        // after a filter is applied we re-observe the list
        viewModel.asteroids.observe(viewLifecycleOwner) {
            asteroidAdapter.submitList(it)
        }

        return true
    }

    private fun setPictureOfDay(pictureOfDay: PictureOfDay) {
        if (pictureOfDay.mediaType == "video") {
            Log.i(TAG, "${pictureOfDay.url} is a video. Not showing picture of the day")
        } else {
            Picasso.with(requireContext())
                .load(pictureOfDay.url)
                .into(binding.activityMainImageOfTheDay)
        }
    }
}
 */

/*
class MainViewModel(application: Application, private val asteroidRepository: Repository) : ViewModel() {

    private val allAsteroids: LiveData<List<Asteroid>> = asteroidRepository.asteroids
    private val todayAsteroids: LiveData<List<Asteroid>> = asteroidRepository.todayAsteroids
    private val todayHazardous: LiveData<List<Asteroid>> = asteroidRepository.todayHazardous
    val todayApod: LiveData<PictureOfDay?> = asteroidRepository.todayApod

    val title = Transformations.map(todayApod) {
        //while/when no image is available
        it?.title ?: application.applicationContext.getString(R.string.no_image_title)
    }


    private var filter: MutableLiveData<Int> = MutableLiveData()
    val filteredAsteroids: LiveData<List<Asteroid>> = filter.switchMap {
        when (it) {
            1 -> {
                todayAsteroids
            }
            2 -> {
                allAsteroids
            }
            3 -> todayHazardous
            else -> {
                allAsteroids
            }
        }
    }

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _singleAsteroid = MutableLiveData<Asteroid>()
    val singleAsteroid get() = _singleAsteroid

    init {
        getApod()
        getAsteroids()
        selectFilter(1)
    }

    fun selectFilter(selectedFilter: Int) {
        filter.value = selectedFilter
    }

    private fun getApod() {
        viewModelScope.launch {
            try {
                asteroidRepository.getApod()
            } catch (e: Exception) {

            }
        }

    }

    private fun getAsteroids() {

        viewModelScope.launch {
            try {
                asteroidRepository.getAsteroids()

            } catch (e: Exception) {
                _status.value = e.message
            }
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _singleAsteroid.value = asteroid
    }
}
 */
