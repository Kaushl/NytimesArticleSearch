# NytimesArticleSearch

This is an Android NY Times Article Search app using NY Times. The goal is coding a Article search App that will use NY Times Article search API as data provider.
When developing an android NY Times Article Search app, there are some important aspects to consider: the most important thing is how to use Android Ny Times Article Search!

About the App features:
-> This Article Search APP lets user to search for a particular article and display the gird data and deatils article after clicking  for all deatils about the artilce.
-> Consequently the user can also ask the app to show the particular article on respective search query.
-> App displays the Image icon, content and needed data on main screen and whole description about article on deatils screen .

Additional Features:
-> In addition to article serach, App lets the user to access the deatils for that article clicked.
 -> Can find many article semaelessly with EndlessRecyclerViewScrollListener.

Edge cases that are taken care of:
-> Doesn’t crash when internet is disabled, when there is no network connectivity and when the article not found.
-> Toast messages appears to ask the user to check for internet connection or to enter a valid artilce search query. 
-> Dialog box appears for clicking on the search icons when there is no internet enabled.
->handled the cases for when the configuration changes in the app such as orientation changes (portrait to landscape and vice versa) and multi-window mode changes.

Given more time:
-> More time would have made me implement material design, automated tests with proper MVP pattern. 
->Better UI design.
-> Stores the start date, end date and sort the article by storing the value in shared preferences.
