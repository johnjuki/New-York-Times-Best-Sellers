package com.readingradar.android.ui.screens

//class HomeFragment : Fragment() {
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: MainViewModel by viewModels()
//
//    private lateinit var bestSellersListsAdapter: BestSellersListsAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//
//        val layoutManager = LinearLayoutManager(requireContext())
//        binding.listsRecyclerView.layoutManager = layoutManager
//        bestSellersListsAdapter = BestSellersListsAdapter(this)
//        bestSellersListsAdapter.stateRestorationPolicy =
//            RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//        binding.listsRecyclerView.adapter = bestSellersListsAdapter
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.toolBar.title = getString(R.string.best_sellers)
//
//        viewModel.bestSellersLists.observe(viewLifecycleOwner) {
//            bestSellersListsAdapter.addData(it)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}