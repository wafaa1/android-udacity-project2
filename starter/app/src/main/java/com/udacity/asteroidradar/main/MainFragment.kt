package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

//    private lateinit var binding: FragmentMainBinding

    /**
     * Lazily initialize our [MainViewModel].
     */
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, MainViewModel.MainViewModelFactory(activity.application)).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val binding = FragmentMainBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        binding.asteroidRecycler.adapter = AsteroidAdapter(
            AsteroidAdapter.OnClickListener {
                viewModel.displayAsteroidDetails(it)
            })

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MainViewModel
        binding.viewModel = viewModel


        viewModel.navigateToSelectedAsteroid.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayAsteroidDetailsComplete()
            }
        })

            setHasOptionsMenu(true)

            return binding.root
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_week_menu -> MainViewModel.AsteroidFilter.SHOW_WEEK
                R.id.show_today_menu -> MainViewModel.AsteroidFilter.SHOW_TODAY
                else -> MainViewModel.AsteroidFilter.SHOW_SAVED
            }
        )
        return true
    }
}




