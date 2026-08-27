# Development

`cd styles; npm run watch`
`hugo server`

# Images in posts

Put the files in `assets/images/blog/<post-slug>/` and reference them from markdown, without a leading
slash:

```markdown
![alt text](images/blog/my-post/photo.jpg)

![alt text](images/blog/my-post/photo.jpg "Caption below the image, *markdown* allowed")
```

`layouts/_default/_markup/render-image.html` then resizes each image to 480/720/1080/1440px wide (never
upscaling), converts it to webp with an original-format fallback, and emits `srcset`/`sizes` - so a phone
downloads a small variant, a retina desktop a large one. Provide originals at least 1440px wide: the
content column is 720px, and anything narrower is upscaled by CSS.

An image alone in its paragraph and given a title becomes a `<figure>` with that title as the caption.

Passed through untouched: remote URLs, absolute paths (the legacy `static/blog/wp-content/uploads/...`
images), svg and gif. A relative path with no matching file under `assets/` fails the build.

Derivatives are generated during the build and cached in `resources/_gen`, which is not committed.

The `coverImage` front matter field - used as the og:image and as the thumbnail in article listings -
takes the same kind of path:

```yaml
coverImage: images/blog/my-post/photo.jpg
```

It is resized by `layouts/partials/cover-image.html`. Absolute paths and remote URLs are used as they
are; without the field, a placeholder is shown.
