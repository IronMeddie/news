package com.example.test1.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val mBinding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()
    var liked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleArgs = args.article
        articleArgs.let { art ->


            viewModel.update(art)

            art.urlToImage.let {

                Glide.with(this).load(it).placeholder(R.drawable.ic_news_default)
                    .into(mBinding.headerImageDetails)
            }
            mBinding.apply {
                headerImageDetails.clipToOutline = true
                detailsTitle.text = art.title
                detailsDescrirtion.text = art.description

                detailsButton.setOnClickListener { view ->
                    try {
                        Intent()
                            .setAction(Intent.ACTION_VIEW)
                            .addCategory(Intent.CATEGORY_BROWSABLE)
                            .setData(Uri.parse(takeIf { URLUtil.isValidUrl(art.url) }?.let {
                                art.url
                            } ?: "https://ya.ru/")).let {
                                ContextCompat.startActivity(requireContext(), it, null)
                            }


                    } catch (e: Exception) {
                        Toast.makeText(context, "Cant open site", Toast.LENGTH_SHORT).show()

                    }
                }

                viewModel.fav.observe(viewLifecycleOwner){
                    if (it)mBinding.iconFavoriteDetails.setImageResource(R.drawable.ic_favorited)
                    else mBinding.iconFavoriteDetails.setImageResource(R.drawable.ic_favorite)
                    liked = it
                }


                mBinding.iconFavoriteDetails.setOnClickListener {
                    if (liked){
                        viewModel.delete(art)
                    } else viewModel.saveToFavorites(article = art)
                }




                iconBack.setOnClickListener {
                    findNavController().popBackStack()
                }
            }


        }

    }

}