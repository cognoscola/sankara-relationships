package com.gorillamo.relationships

import com.gorillamo.relationships.model.Relationship

interface SearchRelationshipsContract {

    /**
     * What the view should do
     */
    interface RelationshipView {

        /**
         * Display the following relationships
         */
        fun displayRelationships(relationships:List<Relationship>)

        /**
         * Display no relationships
         */
        fun displayNoRelationships()

        /**
         * Display error
         */
        fun displayError()

    }

    /**
     * What the presenter should do
     */
    interface RelationshipPresenter{

        /**
         * Show all relationships
         */
        fun loadAll()

        /**
         * load only today's relationshp
         */
        fun loadToday()
    }
}