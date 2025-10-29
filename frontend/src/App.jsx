import { BrowserRouter, Routes, Route } from 'react-router-dom';
import DefaultLayout from './layout/DefaultLayout';
import HomePage from './pages/HomePage';
import FilmDetail from './pages/FilmDetail';
import FilmList from './pages/FilmList';
import GenreList from './pages/GenreList';
import GenreDetail from './pages/GenreDetail';
import { GlobalProvider } from './contexts/GlobalContext';

function App() {
  return (
    <GlobalProvider>
      <BrowserRouter>
        <Routes>
          <Route element={<DefaultLayout />}>
            <Route path='/' element={<HomePage />} />
            <Route path='/films' element={<FilmList />} />
            <Route path='/films/:id' element={<FilmDetail />} />
            <Route path='/genres' element={<GenreList />} />
            <Route path='/genres/:id' element={<GenreDetail />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </GlobalProvider>
  )
}

export default App