package com.gorillamo.relationship.abstraction.extPorts

abstract class UseCaseSync<out R> {
    /**
     * Build the use case to be executed.
     *
     * @return result [R] of the use case.
     */
    protected abstract fun buildUseCase() : R


    /**
     * Execute the use case.
     *
     * Called by client of the use case.
     *
     * @return [R] result of executing this use case
     */
    fun execute(): R = buildUseCase()
}