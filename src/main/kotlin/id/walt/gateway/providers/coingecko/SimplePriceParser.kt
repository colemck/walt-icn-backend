package id.walt.gateway.providers.coingecko

import id.walt.gateway.dto.CoinData
import id.walt.gateway.dto.CoinParameter

class SimplePriceParser(
    private val parameters: CoinParameter,
) : ResponseParser<CoinData> {
    private val coinRegex = "\"%s\":( *)\\{(.|\n)*},?"
    private val priceRegex = "\"%s\":( *)(\\d+.\\d+),?"
    private val marketCapRegex = "\"%s_market_cap\":( *)(\\d+.\\d+),?"
    private val change24hRegex = "\"%s_24h_change\":( *)(-?\\d+.\\d+),?"

    override fun parse(data: String): CoinData = let {
        val match = Regex(String.format(coinRegex, parameters.id)).find(data)
        val coin = match?.groups?.get(0)?.value ?: ""

        val price = Regex(String.format(priceRegex, parameters.currency)).find(coin)
        val marketCap = Regex(String.format(marketCapRegex, parameters.currency)).find(coin)
        val change24h = Regex(String.format(change24hRegex, parameters.currency)).find(coin)

        CoinData(
            price = price?.groups?.get(2)?.value?.toDoubleOrNull() ?: Double.NaN,
            marketCap = marketCap?.groups?.get(2)?.value?.toDoubleOrNull() ?: Double.NaN,
            change = change24h?.groups?.get(2)?.value?.toDoubleOrNull() ?: Double.NaN
        )
    }
}