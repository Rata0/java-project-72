@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Анализатор страниц</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body class="d-flex flex-column min-vh-100">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">Анализатор страниц</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <div class="navbar-nav">
          <a class="nav-link" href="${NamedRoutes.rootPath()}">Главная</a>
          <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
        </div>
      </div>
    </div>
  </nav>

  <main class="flex-grow-1">

    <section>

      <div class="container-fluid bg-dark p-5">
        <div class="row">
          <div class="col-md-10 col-lg-8 mx-auto text-white">
            <h1 class="display-3 mb-0">Анализатор страниц</h1>
            <p class="lead">Бесплатно проверяйте сайты на SEO пригодность</p>
            <form action="/urls" method="post" class="rss-form text-body">
              <div class="row">
                <div class="col">
                  <div class="form-floating">
                    <input id="url-input" autofocus type="text" required name="url" aria-label="url" class="form-control w-100" placeholder="ссылка" autocomplete="off">
                    <label for="url-input">Ссылка</label>
                  </div>
                </div>
                <div class="col-auto">
                  <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Проверить</button>
                </div>
              </div>
            </form>
            <p class="mt-2 mb-0 text-secondary">Пример: https://www.example.com</p>
          </div>
        </div>
      </div>

    </section>
  </main>

  <footer class="footer border-top py-3 mt-5 bg-light">
    <div class="container-xl">
      <div class="text-center">
        created by
        <a href="https://github.com/Rata0" target="_blank">Mataes</a>
      </div>
    </div>
  </footer>
</body>
{content}
</html>