
# Feeds/Events/Logs Publishing (Push vs Pull)
The process of publishing a post to all the followers is called a fanout.

## “Pull” model
### Fan-out-on-load: 
- This method involves keeping all the recent feed data in memory so that users can pull it from the server whenever they need it.
- Clients can pull the feed data on a regular basis or manually whenever they need it.
### 缺点
1. **不及时** New data might not be shown to the users until they issue a pull request
2. **pull的时机可能不好** as most of the time pull requests will result in an empty response if there is no new data, causing waste of resources.

## “Push” model
### Fan-out-on-write: 
- For a push system, once a user has published a post, we can immediately push this post to all the followers.
- To efficiently handle this, users have to maintain a Long Poll request with the server for receiving the updates.
### 优缺点
1. **不用一直扫friend list** and it's significantly reduces read operations.
2. **对大list不友好** when a user has millions of followers (a celebrity-user) the server has to push updates to a lot of people.

## Hybrid model: 
- An alternate method to handle feed data could be to use a hybrid approach, to do a combination of fan-out-on-write and fan-out-on-load. 

### pull for big user, push for small user
- we can stop pushing posts from users with a high number of followers (a celebrity user) and only push data for those users who have a few hundred (or thousand) followers. For celebrity users, we can let the followers pull the updates. Since the push operation can be extremely costly for users who have a lot of friends or followers, by disabling fanout for them, we can save a huge number of resources.

### push for online, pull for offline
- Another alternate approach could be that, once a user publishes a post, we can limit the fanout to only her online friends. Also, to get benefits from both the approaches, a combination of ‘push to notify’ and ‘pull for serving’ end users is a great way to go. Purely a push or pull model is less versatile.
