import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'company',
        data: { pageTitle: 'Companies' },
        loadChildren: () => import('./company/company.module').then(m => m.CompanyModule),
      },
      {
        path: 'person',
        data: { pageTitle: 'People' },
        loadChildren: () => import('./person/person.module').then(m => m.PersonModule),
      },
      {
        path: 'category',
        data: { pageTitle: 'Categories' },
        loadChildren: () => import('./category/category.module').then(m => m.CategoryModule),
      },
      {
        path: 'coach',
        data: { pageTitle: 'Coaches' },
        loadChildren: () => import('./coach/coach.module').then(m => m.CoachModule),
      },
      {
        path: 'video',
        data: { pageTitle: 'Videos' },
        loadChildren: () => import('./video/video.module').then(m => m.VideoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
