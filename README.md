# Instagram_Client - Instagram Client

Instagram Client is an android app that allows you to view popular photos that are currently trending on Instagram. It shows information like person who uploaded the picture, the comments of the people, number of likes etc.

Submitted by: Akshay Kulkarni

Time spent: 25 hours spent in total

## User Stories
The following **required** functionality is completed:

* [x] User can scroll through current popular photos from Instagram
* [x] For each photo displayed, user can see the following details:
      Graphic, Caption, Username
      (Optional) relative timestamp, like count, user profile image

The following **optional** features are implemented:
  
* [x] Advanced: Add pull-to-refresh for popular stream with SwipeRefreshLayout
* [x] Advanced: Show latest comment for each photo (bonus: show last 2 comments)
* [ ] Advanced: Display each photo with the same style and proportions as the real Instagram (see screens below)
* [x] Advanced: Display each user profile image using a RoundedImageView
* [x] Advanced: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
* [ ] Advanced: Improve the user interface through styling and coloring
* [x] Bonus: Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] Bonus: Allow video posts to be played in full-screen using the VideoView
  
## Video Walkthrough 

Here's a walkthrough of implemented user stories:

![Video Walkthrough](Instagram_Client.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I tried implemeting a another activity that shows the entire picture details along with all the comments but the scrolling was not working. I spent around 5-6 hours in order to find out what went wrong and was not able to figure out. Finally, I showed only comments within that activity.

## License

    Copyright [2015] [Akshay Kulkarni]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
