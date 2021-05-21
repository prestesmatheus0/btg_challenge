package com.matheus.btg_challenge.repository

import com.matheus.btg_challenge.network.service.ApiList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListRepository(
    private val remoteApiList : ApiList,
) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)


     fun getCurrencyListFromApi() {
        scope.launch {
            try {
                val currencyListData = remoteApiList.getApiList()

                if(currencyListData.isSuccessful){
                    val currencies = currencyListData.body()?.currencies
                    val typeKeys : MutableList<String> = mutableListOf()

                    currencies.let {
                        typeKeys.addAll(it!!.keys.toList())
                    }

                    println("typekeys $typeKeys")
                }


            }catch (e : Throwable) {
                println("THROW $e")
            }
        }
    }
}