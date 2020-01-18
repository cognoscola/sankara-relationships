Problem:

I have several projects, apps, which use/manipulate entities or models.
This project, for example, uses a Relationship Entity.

A user is able to interact with a relationship in various ways. One way is that a user should
be able to attach "details" to a Relationship. A relationship can have zero, one, or many details.

I know that in the future, i'll have another project, call it Tasks. And each task will have
one or many details.

I'd like it so that I only have to write the "details" system once. Any project should easily (in one line) be
able attach to any of its entities the ability to have details. We shouldn't have to rewrite persistence
logic or add/remove functions of details. The system should already provide that capibility.





