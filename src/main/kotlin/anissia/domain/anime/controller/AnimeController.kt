package anissia.domain.anime.controller


import anissia.domain.anime.command.*
import anissia.domain.anime.model.AnimeItem
import anissia.domain.anime.service.AnimeGenreGenres
import anissia.domain.anime.service.AnimeRankService
import anissia.domain.anime.service.AnimeService
import anissia.domain.session.model.SessionItem
import anissia.infrastructure.common.As
import anissia.infrastructure.common.sessionItem
import anissia.shared.ApiResponse
import anissia.shared.ResultWrapper
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/anime")
class AnimeController(
    private val animeService: AnimeService,
    private val animeGenreGenres: AnimeGenreGenres,
    private val animeRankService: AnimeRankService,
) {
    @GetMapping("/list/{page:\\d+}")
    fun getAnimeList(cmd: GetAnimeListCommand, exchange: ServerWebExchange): Mono<ApiResponse<Page<AnimeItem>>> =
        ResultWrapper.ok(animeService.getList(cmd))

    @GetMapping("/delist")
    fun getAnimeDelist(exchange: ServerWebExchange): Mono<ApiResponse<Page<AnimeItem>>> =
        ResultWrapper.ok(animeService.getDelist(exchange.sessionItem))

    @GetMapping("/animeNo/{animeNo:\\d+}")
    fun getAnime(cmd: GetAnimeCommand, sessionItem: SessionItem, exchange: ServerWebExchange): Mono<ApiResponse<AnimeItem>> =
        ResultWrapper.ok(animeService.get(cmd, sessionItem))

    @GetMapping("/autocorrect")
    fun getAnimeAutocorrect(cmd: GetAutocorrectAnimeCommand, exchange: ServerWebExchange): Mono<ApiResponse<List<String>>> =
        ResultWrapper.ok(animeService.getAutocorrect(cmd))

    @GetMapping("/genres")
    fun getGenres(exchange: ServerWebExchange): Mono<ApiResponse<List<String>>> =
        ResultWrapper.ok(animeGenreGenres.get())

    @GetMapping("/rank/{type}")
    fun getAnimeRank(cmd: GetAnimeRankCommand, exchange: ServerWebExchange): Mono<ApiResponse<List<Map<*,*>>>> =
        ResultWrapper.ok(animeRankService.get(cmd))

    @DeleteMapping("/{animeNo}")
    fun deleteAnime(cmd: DeleteAnimeCommand, exchange: ServerWebExchange): Mono<ApiResponse<Unit>> =
        animeService.delete(cmd, exchange.sessionItem)

    @PostMapping
    fun newAnime(@RequestBody cmd: NewAnimeCommand, exchange: ServerWebExchange): Mono<ApiResponse<Long>> =
        animeService.add(cmd, exchange.sessionItem)

    @PutMapping("/{animeNo}")
    fun editAnime(@RequestBody cmd: EditAnimeCommand, @PathVariable animeNo: Long, exchange: ServerWebExchange): Mono<ApiResponse<Long>> =
        animeService.edit(cmd.apply { this.animeNo = animeNo }, exchange.sessionItem)

    @PostMapping("/recover/{agendaNo}")
    fun recoverAnime(cmd: RecoverAnimeCommand, exchange: ServerWebExchange): Mono<ApiResponse<Long>> =
        animeService.recover(cmd, exchange.sessionItem)
}
