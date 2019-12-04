package com.gorillamo.relationships

interface SearchRelationshipsContract {

    /**
     * What the view should do
     */
    interface RelationshipView {

        /**
         * Display the following relationships
         */
        fun displayRelationships(relationships:List<Relationship>)

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