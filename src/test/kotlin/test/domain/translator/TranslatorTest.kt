package test.domain.translator

import anissia.domain.translator.core.ports.inbound.*

class TranslatorTest(
    private val getApply: GetApply,
    private val getApplyList: GetApplyList,
    private val newApply: NewApply,
    private val newApplyPoll: NewApplyPoll,
    private val getNewTranslatorApplyCount: GetNewTranslatorApplyCount,
) {
//    @GetMapping("/apply/list/{page:\\d+}")
//    fun getApplyList(cmd: GetApplyListCommand, exchange: ServerWebExchange): ResultWrapper<Page<TranslatorApplyItem>> =
//        ResultWrapper.ok(getApplyList.handle(cmd))
//
//    @GetMapping("/apply/{applyNo:\\d+}")
//    fun getApply(cmd: GetApplyCommand, exchange: ServerWebExchange): ResultWrapper<TranslatorApplyItem> =
//        ResultWrapper.ok(getApply.handle(cmd))
//
//    @GetMapping("/apply/count")
//    fun getNewTranslatorApplyCount(exchange: ServerWebExchange): ResultWrapper<Int> =
//        ResultWrapper.ok(getNewTranslatorApplyCount.handle())
//
//    @PostMapping("/apply")
//    fun newApply(@RequestBody cmd: NewApplyCommand, exchange: ServerWebExchange): ResultWrapper<Long> =
//        newApply.handle(cmd, As.toSession(exchange))
//
//    @PostMapping("/apply/{applyNo:\\d+}/poll")
//    fun newApplyPoll(@RequestBody cmd: NewApplyPollCommand, @PathVariable applyNo: Long, exchange: ServerWebExchange): ResultWrapper<Unit> =
//        newApplyPoll.handle(cmd.apply { this.applyNo = applyNo }, As.toSession(exchange))
}
